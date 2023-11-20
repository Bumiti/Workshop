import 'package:get/get.dart';
import 'package:workshop_mobi/api/api_service.dart';
import 'package:workshop_mobi/screens/teacherLayout/teacher_home.dart';
import 'package:workshop_mobi/screens/userLayout/user_home.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';

class RegisterController extends GetxController {
  var isPasswordVisible = false.obs;
final ApiService apiService = ApiService();
  TextEditingController emailController = TextEditingController();
  TextEditingController configEmailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController configPasswordController = TextEditingController();

  bool isSeller = false;
  RxString emailErrorText = ''.obs;
  RxString passwordErrorText = ''.obs;

  void togglePasswordVisibility() {
    isPasswordVisible.value = !isPasswordVisible.value;
  }
  Future<void>RegisterUserAsyn() async{
     if (emailController.text != configEmailController.text) {
      emailErrorText.value = 'Emails do not match';
      return;
    } else {
      emailErrorText.value = '';
    }
     // Kiểm tra xem password và confirmPassword có giống nhau không
    if (passwordController.text != configPasswordController.text) {
      passwordErrorText.value = 'Passwords do not match';
      return;
    } else {
      passwordErrorText.value = '';
    }
    // Kiểm tra xem đã nhập đủ thông tin chưa
    if (emailController.text == '' ||
        configEmailController.text == '' ||
        passwordController.text == '' ||
        configPasswordController.text == '') {
      emailErrorText.value = 'Please enter your email';
      passwordErrorText.value = 'Please enter your password';
      return;
    } else {
      emailErrorText.value = '';
      passwordErrorText.value = '';
    }
    // Thực hiện đăng ký
    try {
      
    } catch (e) {
      
    }
  }
}
