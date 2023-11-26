import 'package:flutter/material.dart';
import 'package:test/components/bottom_nav_bar.dart';
import 'package:test/pages/manage_page.dart';
import 'package:test/pages/hhome_page.dart';
import 'package:test/pages/wallet_page.dart';
import 'package:test/pages/workshop_page.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  //selected index control the bottom nav bar
  int selectedIndex = 0;

  //this will update our selected index
  void navigateBottomBar(int index) {
    setState(() {
      selectedIndex = index;
    });
  }

  //pages to display
  final List<Widget> pages = [
    //Home page
    const HhomePage(),

    //Manage page
    const ManagePage(),

    //Workshop page
    const WorkshopPage(),

    //Wallet page
    const WalletPage(),
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
