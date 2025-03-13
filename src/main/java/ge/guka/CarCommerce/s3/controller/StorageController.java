package ge.guka.CarCommerce.s3.controller;

import ge.guka.CarCommerce.s3.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static ge.guka.CarCommerce.security.AuthorizationConstants.ADMIN;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping("/upload")
    @PreAuthorize(ADMIN)
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file){

        //String imgKey = "cars/" + carId + "/" + file.getOriginalFilename();
        String imgKey = "cars/" + file.getOriginalFilename();
        String imageUrl = storageService.uploadFile(imgKey, file);

        return ResponseEntity.ok(imageUrl);
    }

//    @PostMapping("/upload")
//    public ResponseEntity<Map<String,String>> uploadFile(@RequestParam("file")MultipartFile file){
//
//        try {
//            String key = UUID.randomUUID() + "_" + file.getOriginalFilename();
//
//            Path tempFile = Files.createTempFile("gukas-", file.getOriginalFilename());
//            file.transferTo(tempFile.toFile());
//
//            storageService.uploadFile(key,tempFile.toString());
//
//            URL fileURL = storageService.getFileUrl(key);
//
//            Map<String, String> response = new HashMap<>();
//            response.put("url", file.toString());
//
//            return ResponseEntity.ok(response);
//        }
//        catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("Error", "File uploading error: " + e.getMessage()));
//        }
//    }
}
