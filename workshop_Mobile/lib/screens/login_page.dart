import 'package:flutter/material.dart';
import 'package:workshop_mobi/components/my_button.dart';
import 'package:workshop_mobi/components/my_pw_textfield.dart';
import 'package:workshop_mobi/components/my_email_textfield.dart';

class LoginPage extends StatelessWidget {
  final void Function()? onTap;

  LoginPage({super.key, required this.onTap});

  //text controllers
  final TextEditingController emailController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();

  //login page
  void login() {}

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

              const SizedBox(
                height: 25,
              ),

              //app name

              const Text(
                'Sign In',
                style: TextStyle(fontSize: 50, fontWeight: FontWeight.w700),
              ),

              const SizedBox(
                height: 50,
              ),
              //email textfield
              MyEmailTextfield(obscureText: false, controller: emailController),

              const SizedBox(
                height: 30,
              ),
              //password textfield
              MyPwTextfield(controller: passwordController),
              const SizedBox(
                height: 10,
              ),
              //forgot password
              const Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  Text(
                    "Forgot password?",
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                      color: Color(0xFF00AEEF),
                    ),
                  )
                ],
              ),
              const SizedBox(
                height: 25,
              ),

              //sign in button
              MyButton(
                text: 'Sign in',
                onTap: login,
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
