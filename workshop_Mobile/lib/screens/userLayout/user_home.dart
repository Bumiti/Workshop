import 'package:workshop_mobi/model/user.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:workshop_mobi/screens/auth/widgets/login_page.dart';

class UserHomeScreen extends StatefulWidget {
  const UserHomeScreen({Key? key}) : super(key: key);

  @override
  State<UserHomeScreen> createState() => _UserHomeScreenState();
}

class _UserHomeScreenState extends State<UserHomeScreen> {
  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();
  // final ApiService _apiSer = ApiService();
  List<User> userList = [];
bool showLoginPage = true;

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
             Text('Welcome User Home'),
          ],
        ),
      ),
    );
  }
}