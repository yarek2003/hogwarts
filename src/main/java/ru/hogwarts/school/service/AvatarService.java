package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent() && avatarFile == null) {
            throw new IllegalArgumentException();
        }
        Student student = optionalStudent.orElse(null);
        String originalFileName = avatarFile.getOriginalFilename();

        if (originalFileName == null) {
            throw new IllegalArgumentException();
        }

        Path filePath = Path.of(avatarsDir, studentId + getExtensions(originalFileName));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);//
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findAvatarByStudentId(studentId).orElse(new Avatar());
    }
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
    public List<Avatar> pagesOFAvatar(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return avatarRepository.findAll(pageRequest).getContent();
    }

}
