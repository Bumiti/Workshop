import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:workshop_mobi/controller/authentication/resetPassword_controller.dart';


import 'package:workshop_mobi/screens/auth/components/my_button.dart';
import 'package:workshop_mobi/screens/auth/components/my_email_textfield.dart';
import 'package:workshop_mobi/screens/auth/login_or_register.dart';

// ignore: must_be_immutable
class ForgotPassword extends StatelessWidget {
  final void Function()? onTap;
  ForgotPassword({super.key, required this.onTap});
  ResetPassWordController resetPasswordController = Get.put(ResetPassWordController());

final GlobalKey<FormState> formKey = GlobalKey<FormState>();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      // Theme.of(context).colorScheme.background,
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(25),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Text(
                'Forgot Password',
                style: TextStyle(fontSize: 36, fontWeight: FontWeight.bold),
              ),

              const SizedBox(
                height: 50,
              ),

              //username textfield
              MyEmailTextfield(
                  obscureText: false,
                  controller: resetPasswordController.emailController, formKey: formKey,),

              const SizedBox(
                height: 10,
              ),

              //email textfield
            
              const SizedBox(
                height: 10,
              ),
              const SizedBox(
                height: 25,
              ),

              //register button
              MyButton(
                text: 'Reset Password',
                onTap:() async{
                   await resetPasswordController.resetPasswordAsyn();
                },
              ),

              const SizedBox(
                height: 25,
              ),

              //don't have an account? Register here
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  const Text(
                    "Already have an account? ",
                    style: TextStyle(color: Colors.black),
                  ),
                  GestureDetector(
                    onTap:  () {
                      // Điều hướng đến trang Forgot Password
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => LoginOrReg()),
                      );
                    },
                    child: const Text(
                      "Login here",
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                        color: Color(0xFF00AEEF),
                      ),
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
