import 'package:flutter/material.dart';
import 'package:workshop_mobi/screens/auth/login_or_register.dart';
import 'package:workshop_mobi/screens/teacherLayout/teacher_home.dart';
import 'package:workshop_mobi/screens/userLayout/user_home.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  final FlutterSecureStorage storage =  FlutterSecureStorage();
  String? token = await storage.read(key: 'token');
  String? roles = await storage.read(key: 'roles');

  runApp(MyApp(token,roles));
}

class MyApp extends StatelessWidget {
  final String? token;
  final String? roles;
  const MyApp(this.token,this.roles, {super.key});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      debugShowCheckedModeBanner: false,
      home: token != null ? getHomeScreen() : const LoginOrReg(),
    );
  }

  Widget getHomeScreen() {
    // print(token);
    //  print(roles);
    if (token!=null && roles == 'USER') {
      return const UserHomeScreen();
    } else if (token!=null && roles == 'SELLER') {
      return const TeacherHomeScreen();
    } else {

      return const LoginOrReg();
    }
  }
}
