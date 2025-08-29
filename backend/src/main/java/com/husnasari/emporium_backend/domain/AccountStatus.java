package com.husnasari.emporium_backend.domain;

public enum AccountStatus {

    PENDING_VERIFICATION, // Hesap oluşturuldu ancak henüz doğrulanmadı
    ACTIVE, // Hesap aktif ve iyi durumda.
    SUSPENDED, // Hesap geçici olarak askıya alındı, muhtemelen ihlaller nedeniyle
    DEACTIVETED, // Hesap kalıcı olarak kapatıldı, muhtemelen kullanıcının isteği üzerine
    BANNED, // Hesap ciddi ihlaller nedeniyle kalıcı olarak yasaklandı
    CLOSED // Hesap kalıcı olarak kapatıldı, muhtemelen kullanıcının isteği üzerine
}
