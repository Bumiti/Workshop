import 'package:flutter/material.dart';
import 'package:workshop_mobi/controller/authentication/login_controller.dart';
import 'package:workshop_mobi/screens/auth/components/my_button.dart';
import 'package:workshop_mobi/screens/auth/components/my_pw_textfield.dart';
import 'package:workshop_mobi/screens/auth/components/my_email_textfield.dart';

import 'package:get/get.dart';
import 'package:workshop_mobi/screens/auth/widgets/forgot_password.dart';

// ignore: must_be_immutable
class LoginPage extends StatelessWidget {
  final void Function()? onTap;

  LoginPage({super.key, required this.onTap});
  LoginController loginController = Get.put(LoginController());
// 1. Tạo GlobalKey<FormState> ở mức cao nhất
  final GlobalKey<FormState> emailformKey = GlobalKey<FormState>();
  final GlobalKey<FormState> passwordformKey = GlobalKey<FormState>();
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
                  controller: loginController.emailController, formKey: emailformKey,),

              const SizedBox(
                height: 30,
              ),
              //password textfield
              MyPwTextfield(controller: loginController.passwordController,formKey:passwordformKey, obscureText: true,),
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
