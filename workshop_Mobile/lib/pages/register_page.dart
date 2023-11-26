import 'package:flutter/material.dart';
import 'package:test/components/my_button.dart';
import 'package:test/components/my_email_textfield.dart';
import 'package:test/components/my_emailconfirm_textfield.dart';
import 'package:test/components/my_pw_textfield.dart';
import 'package:test/components/my_pwconfirm_textfield.dart';

class RegisterPage extends StatelessWidget {
  final void Function()? onTap;

  RegisterPage({super.key, required this.onTap});

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
                'Sign up',
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

              //password textfield
              MyPwTextfield(controller: passwordController),
              const SizedBox(
                height: 10,
              ),

              //Confirmpassword textfield
              MyConfirmPwTextfield(controller: confirmPwdController),
              const SizedBox(
                height: 10,
              ),

              //forgot password
              // Row(
              //   mainAxisAlignment: MainAxisAlignment.end,
              //   children: [
              //     Text(
              //       "Forgot password?",
              //       style: TextStyle(
              //           color: Theme.of(context).colorScheme.inversePrimary),
              //     ),
              //   ],
              // ),
              const SizedBox(
                height: 25,
              ),

              //register button
              MyButton(
                text: 'Register',
                onTap: register,
              ),

              const SizedBox(
                height: 25,
              ),

              //don't have an account? Register here
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    "Already have an account? ",
                    style: TextStyle(
                        color: Theme.of(context).colorScheme.inversePrimary),
                  ),
                  GestureDetector(
                    onTap: onTap,
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
