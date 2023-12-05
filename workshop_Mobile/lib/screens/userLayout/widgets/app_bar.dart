import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:workshop_mobi/api/api_service.dart';
import 'package:workshop_mobi/controller/authentication/login_controller.dart';
import 'package:workshop_mobi/screens/auth/login_or_register.dart';
import 'package:workshop_mobi/screens/userLayout/widgets/custom_logo_appbar.dart';
import 'package:workshop_mobi/screens/userLayout/widgets/studen_info.dart';


class CustomAppBar extends StatelessWidget implements PreferredSizeWidget {
  
  const CustomAppBar({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    LoginController loginController = Get.put(LoginController());

    final apiService = ApiService();

    const storage = FlutterSecureStorage();
    
    final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();
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
          onSelected: (value) async {
            if (value == 'profile') {
              var accessToken = await storage.read(key: 'token');
              // Use the token in your API request
              var response = await apiService.getinforStudent(accessToken!);
              // ignore: use_build_context_synchronously
              Navigator.push(
                context,
                MaterialPageRoute(
                    builder: (context) => ProfilePage(
                          userInfoResponse: response,
                        )),
              );
            } else if (value == 'settings') {
            } else if (value == 'logout') {
              () async {
                final SharedPreferences prefs = await _prefs;
                prefs.clear();
                Get.offAll(const LoginOrReg());
              }();
            }
          },
          icon: CircleAvatar(
            // backgroundImage: AssetImage('lib/assets/Logo.png'),
            // Using Obx to listen for changes in the imageUrl
            
            child: Obx(() {
              print(loginController.imageUrl.value);
              if (loginController.imageUrl.value.isNotEmpty) {
                return Image(
                    image: NetworkImage(loginController.imageUrl.value));
              } else if(loginController.image0AuthenUrl!.value.isNotEmpty) {
                return Image(
                    image: NetworkImage(loginController.image0AuthenUrl!.value));
              }else {
                return Image(image: AssetImage('lib/assets/Logo.png'));
              }
            }),
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
