# Android Ürün Tanıtımı

Bu uygulama; bir şirketin ürünlerini, kayıtlı kullanıcılara servis üzerinden listeler. Kullanıcılar, ürün listesi içinden istedikleri ürünü seçip, detay sayfalarında sipariş verebilirler. Tüm servis bağlantısı aşamalarında Retrofit kütüphanesinden yardım alınır.

| Kullanıcı Adı | Şifre |
| ------------- | ------------- |
| 🧔 yunus@mail.com  | 🔒 1234  |
| 👨‍🦱 yunus2@mail.com  | 🔒 1234  | 

## Giriş Ekranı 
Kullanıcı sisteme kayıtlı mail adresi ve şifresi ile giriş yapabilir. Son başarılı giriş yapılan mail adresi otomatik olarak giriş kutusunu doldurur. Kullanıcının kaydı yoksa kayıt ol butonuyla kayıt olabilir. Kullanıcı bilgileri eksik ise uygulamadan hata döner, bilgiler hatalıysa servis tarafından hata döner.

<a href="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/login.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/login.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/login_error.pngg" target="_blank">
<img src="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/login_error.png" width="200" style="max-width:100%;"></a>

## Kayıt Ekranı
Giriş ekranı gibi kullanıcı bilgileri eksik ise uygulamadan hata döner, bilgiler hatalıysa servis tarafından hata döner. Kayıt başarılı bir şekilde tamamlanırsa, uygulama giriş ekranına döner ve email kutusunda, kayıt olunan email adresi yazar.

<a href="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/register_error.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/register_error.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/register_reuse.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/register_reuse.png" width="200" style="max-width:100%;"></a>

## Ürün Ekranları
Başarılı bir giriş sonrası servis tarafından alınan ürün listesi açılır. Buradan istenilen ürüne tıklanınca onun detay sayfası gelir.

<a href="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/product_list.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/product_list.png" width="200" style="max-width:100%;"></a>
  
<a href="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/product_detail.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/product_detail.png" width="200" style="max-width:100%;"></a>
 
## Satın Alma
Ürün detayı sayfasından ürün satın alınabilir. Satın alma talebi servise gönderilir ve servis tarafından gelen yanıt kullanıcıya gösterilir. Servise talep gitmez ise bağlantı hatası kullanıcıya gösterilir. Satın alma sonrasında hataların önüne geçebilmek için buton pasif duruma alınır.

<a href="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/product_detail_buy.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/product_detail_buy.png" width="200" style="max-width:100%;"></a>
  
<a href="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/product_detail_no_internet.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Urun-Tanitimi/blob/main/app_images/product_detail_no_internet.png" width="200" style="max-width:100%;"></a>
  
