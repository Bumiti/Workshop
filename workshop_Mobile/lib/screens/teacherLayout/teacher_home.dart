import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:workshop_mobi/components/bottom_nav_bar.dart';
import 'package:workshop_mobi/screens/auth/login_or_register.dart';
import 'package:workshop_mobi/screens/home_page.dart';
import 'package:workshop_mobi/screens/teacherLayout/widgets/manage_page.dart';
import 'package:workshop_mobi/screens/teacherLayout/widgets/qr_scan.dart';
import 'package:workshop_mobi/screens/teacherLayout/widgets/wallet_page.dart';
import 'package:workshop_mobi/screens/teacherLayout/widgets/workshop_page.dart';


class TeacherHomeScreen extends StatefulWidget {
  const TeacherHomeScreen({super.key});

  @override
  State<TeacherHomeScreen> createState() => _TeacherHomeScreenState();
  
}

class _TeacherHomeScreenState extends State<TeacherHomeScreen> {
   final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();
  int selectedIndex = 0;
  void navigateBottomBar(int index) {
    setState(() {
      selectedIndex = index;
    });
  }

  //pages to display
  final List<Widget> pages = [
    //Home page
    const PublicHomeLanding(),

    //Manage page
    const ManagePage(),

    //Workshop page
    const WorkshopPage(),

    //Wallet page
    const WalletPage(),

    const QRViewExample(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[300],
      bottomNavigationBar: MyBottomNavBar(
        onTabChange: (index) => navigateBottomBar(index),
      ),
      appBar: AppBar(
          backgroundColor: Colors.white,
          elevation: 0,
          leading: Builder(
            builder: (context) => IconButton(
              icon: const Icon(
                Icons.menu_rounded,
                color: Colors.black,
              ),
              onPressed: () {
                Scaffold.of(context).openDrawer();
              },
            ),
          )),
      drawer: Drawer(
        backgroundColor: Colors.grey.shade300,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            //logo
            Column(
              children: [
                DrawerHeader(
                  child: Image.asset(
                    'lib/assets/Logo.png',
                    width: 100,
                    height: 100,
                  ),
                ),

                const Padding(
                  padding: EdgeInsets.symmetric(horizontal: 25.0),
                  child: Divider(color: Colors.white),
                ),

                //other pages
                Padding(
                  padding: const EdgeInsets.only(left: 25),
                  child: ListTile(
                    leading: Icon(
                      Icons.home,
                      color: Colors.grey[700],
                    ),
                    title: const Text(
                      'Home',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                  ),
                ),

                Padding(
                  padding: const EdgeInsets.only(left: 25),
                  child: ListTile(
                    leading: Icon(
                      Icons.info,
                      color: Colors.grey[700],
                    ),
                    title: const Text(
                      'Info',
                      style: TextStyle(fontWeight: FontWeight.bold),
                    ),
                  ),
                ),
              ],
            ),
            Padding(
              padding: const EdgeInsets.only(left: 25),
              child: ListTile(
                onTap: () async{
                  final SharedPreferences prefs = await _prefs;
                  prefs.clear();
                   Get.offAll(const LoginOrReg());
                },
                leading: Icon(
                  Icons.logout_rounded,
                  color: Colors.grey[700],
                ),
                title: const Text(
                  'Log out',
                  style: TextStyle(fontWeight: FontWeight.bold),
                ),
              ),
            )
          ],
        ),
      ),
      body: pages[selectedIndex],
    );
  }
}
