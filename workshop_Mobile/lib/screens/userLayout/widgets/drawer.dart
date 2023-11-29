import 'package:flutter/material.dart';

class MyDrawer extends StatelessWidget {
  final VoidCallback onLogout;

  const MyDrawer({Key? key, required this.onLogout}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: Column(
        children: [
          UserAccountsDrawerHeader(
            accountName: const Text('Lê Thanh Hiếu'),
            accountEmail: const Text('hieu6422@gmail.com'),
            currentAccountPicture: CircleAvatar(
              child: ClipOval(child: Image.asset('lib/assets/Logo.png')),
            ),
            decoration: const BoxDecoration(
                color: Colors.pinkAccent,
                // ignore: unnecessary_const
                image: const DecorationImage(
                    image: AssetImage('lib/assets/background.jpg'),
                    fit: BoxFit.cover)),
          ),
          ListTile(
            leading: const Icon(Icons.file_upload),
            title: const Text('Upload shot'),
            // ignore: avoid_print
            onTap: () => print('Upload tapped'),
          ),
          ListTile(
            leading: const Icon(Icons.share),
            title: const Text('Share'),
            // ignore: avoid_print
            onTap: () => print('Upload tapped'),
          ),
          ListTile(
            leading: const Icon(Icons.notifications),
            title: const Text('Notifications'),
            // ignore: avoid_print
            onTap: () => print('Upload tapped'),
          ),
          ListTile(
            leading: const Icon(Icons.settings),
            title: const Text('Settings'),
            // ignore: avoid_print
            onTap: () => print('Upload tapped'),
          ),
          const Spacer(), // Add Spacer to push "Sign Out" to the bottom
          Align(
            alignment: Alignment.bottomLeft,
            child: ListTile(
              leading: const Icon(Icons.logout),
              title: const Text('Sign Out'),
              onTap: onLogout,
            ),
          ),
        ],
      ),
    );
  }
}
