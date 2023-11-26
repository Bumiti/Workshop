import 'package:flutter/material.dart';
import 'package:google_nav_bar/google_nav_bar.dart';

class MyBottomNavBar extends StatelessWidget {
  final void Function(int)? onTabChange;
  MyBottomNavBar({super.key, required this.onTabChange});

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 15, vertical: 20),
        child: GNav(
          backgroundColor: Colors.white,
          color: Colors.black,
          activeColor: Colors.white70,
          tabBackgroundColor: Colors.grey.shade600,
          onTabChange: (value) => onTabChange!(value),
          gap: 8,
          padding: const EdgeInsets.all(16),
          tabs: const [
            GButton(
              icon: Icons.home_filled,
              text: 'Home',
            ),
            GButton(
              icon: Icons.snippet_folder_outlined,
              text: 'Manage',
            ),
            GButton(
              icon: Icons.people_alt_outlined,
              text: 'Workshop',
            ),
            GButton(
              icon: Icons.wallet,
              text: 'Wallet',
            ),
          ],
        ),
      ),
    );
  }
}
