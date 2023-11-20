import 'package:flutter/material.dart';
import 'package:workshop_mobi/screens/auth/components/my_button.dart';
import 'package:workshop_mobi/screens/auth/components/my_pw_textfield.dart';
import 'package:workshop_mobi/screens/auth/components/my_email_textfield.dart';
import 'package:workshop_mobi/controller/login_controller.dart';
import 'package:get/get.dart';
import 'package:workshop_mobi/screens/auth/widgets/forgot_password.dart';

// ignore: must_be_immutable
class LoginPage extends StatelessWidget {
  final void Function()? onTap;

  LoginPage({super.key, required this.onTap});
  LoginController loginController = Get.put(LoginController());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(25),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              //logo
              // Image.asset(
              //   "lib/assets/Logo.jpg",
              //   width: 200,
              //   height: 200,
              // ),
              // Icon(
              //   Icons.person,
              //   size: 80,
              //   color: Theme.of(context).colorScheme.inversePrimary,
              // ),
              // const SizedBox(
              //   height: 25,
              // ),
              //app name
              const Text(
                'Sign In',
                style: TextStyle(fontSize: 50, fontWeight: FontWeight.w700),
              ),

              const SizedBox(
                height: 50,
              ),
              //email textfield
              MyEmailTextfield(
                  obscureText: false,
                  controller: loginController.emailController),

              const SizedBox(
                height: 30,
              ),
              //password textfield
              MyPwTextfield(controller: loginController.passwordController),
              const SizedBox(
                height: 10,
              ),
              //forgot password
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  GestureDetector(
                    onTap: () {
                      // Điều hướng đến trang Forgot Password
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => ForgotPassword(onTap: () {  },)),
                      );
                    },
                    child: Text(
                      "Forgot password?",
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                        color: Color(0xFF00AEEF),
                      ),
                    ),
                  ),
                ],
              ),

              const SizedBox(
                height: 25,
              ),

              //sign in button
              MyButton(
                text: 'Sign in',
                onTap: () async {
                  await loginController.loginWithEmail();
                },
              ),

              const SizedBox(
                height: 25,
              ),

              //don't have an account? Register here
              Row(
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  const Text(
                    "Don't have an account? ",
                    style: TextStyle(color: Colors.black),
                  ),
                  GestureDetector(
                    onTap: onTap,
                    child: const Text(
                      "Register here",
                      style: TextStyle(
                          fontWeight: FontWeight.bold,
                          color: Color(0xFF00AEEF)),
                    ),
                  ),
                ],
              )
            ],
          ),
        ),
      ),
    );
  }
}
