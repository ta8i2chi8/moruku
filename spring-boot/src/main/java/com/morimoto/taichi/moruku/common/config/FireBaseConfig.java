// package com.morimoto.taichi.moruku.common.config;

// import java.io.FileInputStream;
// import java.io.IOException;
// import java.nio.file.Paths;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;

// @Configuration
// public class FireBaseConfig{
//     @Bean
//     FirebaseApp createFireBaseApp() throws IOException {
//         // 相対パスを使わずにresources直下のパスを取得するために、Paths.get()を使用
//         FileInputStream serviceAccount = new FileInputStream(Paths.get("serviceAccountKey.json").toString());

//         FirebaseOptions options = FirebaseOptions.builder()          
//             .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//             .build();

//         return FirebaseApp.initializeApp(options);
//     }
// }