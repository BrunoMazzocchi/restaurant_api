package com.example.restaurant_api.persistance.data;

import lombok.*;
import org.springframework.web.multipart.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadPictureForm {
    private MultipartFile picture;
}
