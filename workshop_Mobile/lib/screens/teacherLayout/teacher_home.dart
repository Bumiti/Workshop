import 'package:workshop_mobi/model/user.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:workshop_mobi/screens/auth/widgets/login_page.dart';

class TeacherHomeScreen extends StatefulWidget {
  const TeacherHomeScreen({Key? key}) : super(key: key);

  @override
  State<TeacherHomeScreen> createState() => _TeacherScreenState();
}

class _TeacherScreenState extends State<TeacherHomeScreen> {
  bool showLoginPage = true;
  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();
  // final ApiService _apiSer = ApiService();
  List<User> userList = [];

 //toggle
  void togglePages() {
    setState(() {
      showLoginPage = !showLoginPage;
    });
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(actions: [
        TextButton(
          onPressed: () async {
            final SharedPreferences prefs = await _prefs;
            prefs.clear();
            Get.offAll(LoginPage(onTap: togglePages));
          },
          child: const Text(
            'logout',
            style: TextStyle(color: Colors.white),
          ),
        )
      ]),
      body:const Center(
        child: Column(
          children: [
             Text('Welcome Teacher Home'),
          ],
        ),
      ),
    );
  }
}