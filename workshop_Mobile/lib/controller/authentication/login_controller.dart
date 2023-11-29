import 'package:workshop_mobi/api/api_service.dart';
import 'package:workshop_mobi/api/google_signin_api.dart';
import 'package:workshop_mobi/screens/teacherLayout/teacher_home.dart';
import 'package:workshop_mobi/screens/userLayout/user_home.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:google_sign_in/google_sign_in.dart';

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
      const storage = FlutterSecureStorage();
      String roleString = '';
      if (roles != null && roles.isNotEmpty) {
        roleString = roles[0];
      } else {
        // print("Roles list is empty or null");
      }
      var accessToken = user['accessToken'];
      var userName = user['user_name'];
      await prefs.setString('token', accessToken);
      await storage.write(key: 'token', value: accessToken);
      await storage.write(key: 'roles', value: roleString);
      await storage.write(key: 'userName', value: userName);
      emailController.clear();
      
      passwordController.clear();
      if (roleString == 'USER') {
        Get.off(const UserHomeScreen());
      } else if (roleString == 'SELLER') {
        Get.off(const TeacherHomeScreen());
      } else if (roleString == 'ADMIN') {
        showDialog(
          context: Get.context!,
          builder: (context) {
            return const SimpleDialog(
              title: Text('False'),
              contentPadding: EdgeInsets.all(20),
              children: [
                Text(
                    'ADMIN CAN NOT LOGIN PRODUCT APP')
              ],
            );
          },
        );
      }
    } catch (error) {
   
      showDialog(
        context: Get.context!,
        builder: (context) {
          return const SimpleDialog(
            title: Text('Error'),
            contentPadding: EdgeInsets.all(20),
            children: [
              Text(
                  'Your Account Not Available for this App, Please Register First')
            ],
          );
        },
      );
    }
  }
  Future<void> loginWithGoogle() async {
  final user =   await GoogleSignInApi.login();
  // print(user);
  // print(user?.email);
  String? email = user?.email;
  try {
      var user = await apiService.login0AuthenAccount(
        email!,
      );
      print(user);
      final SharedPreferences prefs = await _prefs;
      var roles = user['roles'];
      const storage = FlutterSecureStorage();
      String roleString = '';
      if (roles != null && roles.isNotEmpty) {
        roleString = roles[0];
      } else {
       
      }
      var accessToken = user['accessToken'];
      var userName = user['user_name'];
      await prefs.setString('token', accessToken);
      await storage.write(key: 'token', value: accessToken);
      await storage.write(key: 'roles', value: roleString);
      await storage.write(key: 'userName', value: userName);
      emailController.clear();
      passwordController.clear();
      if (roleString == 'USER') {
        Get.off(const UserHomeScreen());
      } else if (roleString == 'SELLER') {
        Get.off(const TeacherHomeScreen());
      } else if (roleString == 'ADMIN'){
        showDialog(
          context: Get.context!,
          builder: (context) {
            return const SimpleDialog(
              title: Text('False'),
              contentPadding: EdgeInsets.all(20),
              children: [
                Text(
                    'ADMIN CAN NOT LOGIN PRODUCT APP')
              ],
            );
          },
        );
      }
    } catch (error) {
   
      showDialog(
        context: Get.context!,
        builder: (context) {
          return const SimpleDialog(
            title: Text('Error'),
            contentPadding: EdgeInsets.all(20),
            children: [
              Text(
                  'Your Account Not Available for this App, Please Register First')
            ],
          );
        },
      );
    }
  }
}
