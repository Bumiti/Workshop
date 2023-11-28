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
            decoration: BoxDecoration(
                color: Colors.pinkAccent,
                image: DecorationImage(
                    image: AssetImage('lib/assets/background.jpg'),
                    fit: BoxFit.cover)),
          ),
          ListTile(
            leading: Icon(Icons.file_upload),
            title: Text('Upload shot'),
            onTap: () => print('Upload tapped'),
          ),
          ListTile(
            leading: Icon(Icons.share),
            title: Text('Share'),
            onTap: () => print('Upload tapped'),
          ),
          ListTile(
            leading: Icon(Icons.notifications),
            title: Text('Notifications'),
            onTap: () => print('Upload tapped'),
          ),
          ListTile(
            leading: Icon(Icons.settings),
            title: Text('Settings'),
            onTap: () => print('Upload tapped'),
          ),
          Spacer(), // Add Spacer to push "Sign Out" to the bottom
          Align(
            alignment: Alignment.bottomLeft,
            child: ListTile(
              leading: Icon(Icons.logout),
              title: Text('Sign Out'),
              onTap: onLogout,
            ),
          ),
        ],
      ),
    );
  }
}
