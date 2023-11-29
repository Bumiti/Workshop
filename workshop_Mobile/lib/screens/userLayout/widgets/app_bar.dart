import 'package:flutter/material.dart';
import 'package:workshop_mobi/screens/userLayout/widgets/custom_logo_appbar.dart';

class CustomAppBar extends StatelessWidget implements PreferredSizeWidget {
  const CustomAppBar({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Colors.white,
      elevation: 0,
      centerTitle: true,
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
      ),
      title: CustomLogoAppBar(), // Customize the title as needed
      actions: [
        // Use PopupMenuButton for notifications
        PopupMenuButton<String>(
          onSelected: (value) {
            // Handle the selected value if needed
          },
          icon: const Icon(
            Icons.notifications,
            color: Colors.black,
          ),
          offset: const Offset(
            0,
            60,
          ), // Adjust the offset as needed
          itemBuilder: (BuildContext context) => <PopupMenuEntry<String>>[
            const PopupMenuItem<String>(
              value: 'item1',
              child: Text('Notification 1'),
            ),
            const PopupMenuItem<String>(
              value: 'item2',
              child: Text('Notification 2'),
            ),
          ],
        ),

        // Use PopupMenuButton for CircleAvatar options
        PopupMenuButton<String>(
          onSelected: (value) {
            // Handle the selected value if needed
            if (value == 'profile') {
              // Show profile page
            } else if (value == 'settings') {
              // Show settings page
            } else if (value == 'logout') {
              // Perform logout
            }
          },
          icon: const CircleAvatar(
            backgroundImage: AssetImage(
                'lib/assets/Logo.png'), // Replace with your image path
          ),
          offset: const Offset(
            0,
            60,
          ), // Adjust the offset as needed
          itemBuilder: (BuildContext context) => <PopupMenuEntry<String>>[
            const PopupMenuItem<String>(
              value: 'profile',
              child: Text('Profile'),
            ),
            const PopupMenuItem<String>(
              value: 'settings',
              child: Text('Settings'),
            ),
            const PopupMenuItem<String>(
              value: 'logout',
              child: Text('Logout'),
            ),
          ],
        ),
      ],
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);
}
