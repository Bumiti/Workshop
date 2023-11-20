import 'package:workshop_mobi/api/api_service.dart';
import 'package:workshop_mobi/screens/teacherLayout/teacher_home.dart';
import 'package:workshop_mobi/screens/userLayout/user_home.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LoginController extends GetxController {
  final ApiService apiService = ApiService();
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();

  Future<void> loginWithEmail() async {
    try {
      var user = await apiService.loginWebAccount(
        emailController.text.trim(),
        passwordController.text,
      );
      final SharedPreferences prefs = await _prefs;
      var roles = user['roles'];
      String roleString = '';
      if (roles != null && roles.isNotEmpty) {
         roleString = roles[0];
      } else {
        // print("Roles list is empty or null");
      }
      var accessToken = user['accessToken'];
      // print(roleString);
      await prefs.setString('token', accessToken);

      emailController.clear();
      passwordController.clear();
      if (roleString == 'USER') {
        Get.off(const UserHomeScreen());
      } else if (roleString == 'SELLER') {
        Get.off(const TeacherHomeScreen());
      }else{
        Get.back();
        showDialog(
        context: Get.context!,
        builder: (context) {
          return const SimpleDialog(
            title: Text('Warning'),
            contentPadding: EdgeInsets.all(20),
            children: [Text('Your Account Not Available for this App, Please Login First')],
          );
        },
      );
      }
    } catch (error) {
      Get.back();
      showDialog(
        context: Get.context!,
        builder: (context) {
          return SimpleDialog(
            title: const Text('Error'),
            contentPadding: const EdgeInsets.all(20),
            children: [Text(error.toString())],
          );
        },
      );
    }
  }
}
