import 'package:flutter/material.dart';
import 'package:workshop_mobi/screens/auth/components/my_button.dart';
import 'package:workshop_mobi/screens/auth/components/my_email_textfield.dart';
import 'package:workshop_mobi/screens/auth/components/my_emailconfirm_textfield.dart';
import 'package:workshop_mobi/screens/auth/login_or_register.dart';


class ForgotPassword extends StatelessWidget {
  final void Function()? onTap;

  ForgotPassword({super.key, required this.onTap});
  //text controllers
  final TextEditingController usernameController = TextEditingController();
  final TextEditingController emailController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();
  final TextEditingController confirmPwdController = TextEditingController();

  //register method
  void register() {}

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
              //logo
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
                'Forgot Password',
                style: TextStyle(fontSize: 36, fontWeight: FontWeight.bold),
              ),

              const SizedBox(
                height: 50,
              ),

              //username textfield
              MyEmailTextfield(
                  obscureText: false, controller: usernameController),

              const SizedBox(
                height: 10,
              ),

              //email textfield
              MyConfirmEmailTextfield(
                  obscureText: false, controller: emailController),

              const SizedBox(
                height: 10,
              ),
              const SizedBox(
                height: 25,
              ),

              //register button
              MyButton(
                text: 'Reset Password',
                onTap: register,
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
