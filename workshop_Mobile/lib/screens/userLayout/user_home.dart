import 'package:workshop_mobi/model/user.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:workshop_mobi/screens/auth/login_or_register.dart';
import 'package:workshop_mobi/screens/userLayout/widgets/app_bar.dart';
import 'package:workshop_mobi/screens/userLayout/widgets/bottom_nav_bar_user.dart';
import 'package:workshop_mobi/screens/userLayout/widgets/custom_logo_appbar.dart';
import 'package:workshop_mobi/screens/userLayout/widgets/drawer.dart';
import 'package:workshop_mobi/screens/teacherLayout/widgets/home_page.dart';
import 'package:workshop_mobi/screens/teacherLayout/widgets/manage_page.dart';
import 'package:workshop_mobi/screens/teacherLayout/widgets/qr_scan.dart';
import 'package:workshop_mobi/screens/teacherLayout/widgets/wallet_page.dart';
import 'package:workshop_mobi/screens/teacherLayout/widgets/workshop_page.dart';

class UserHomeScreen extends StatefulWidget {
  const UserHomeScreen({Key? key}) : super(key: key);

  @override
  State<UserHomeScreen> createState() => _UserHomeScreenState();
}

class _UserHomeScreenState extends State<UserHomeScreen> {
  final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();
  List<User> userList = [];
  bool showLoginPage = true;
  int selectedIndex = 0;

  void navigateBottomBar(int index) {
    setState(() {
      selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[300],
      bottomNavigationBar: MyBottomNavBar(
        onTabChange: (index) => navigateBottomBar(index),
      ),
      appBar: CustomAppBar(),
      drawer: MyDrawer(onLogout: () async {
        final SharedPreferences prefs = await _prefs;
        prefs.clear();
        Get.offAll(const LoginOrReg());
      }),
      body: pages[selectedIndex],
    );
  }

  //pages to display
  final List<Widget> pages = [
    //Home page
    const HomePage(),

    //Manage page
    const ManagePage(),

    // //Workshop page
    const WorkshopPage(),

    // //Wallet page
    const WalletPage(),
  ];
}
