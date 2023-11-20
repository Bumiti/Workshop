import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:workshop_mobi/controller/register_controller.dart';
import 'package:workshop_mobi/screens/auth/components/my_button.dart';
import 'package:workshop_mobi/screens/auth/components/my_email_textfield.dart';
import 'package:workshop_mobi/screens/auth/components/my_emailconfirm_textfield.dart';
import 'package:workshop_mobi/screens/auth/components/my_pw_textfield.dart';
import 'package:workshop_mobi/screens/auth/components/my_pwconfirm_textfield.dart';

class RegisterPage extends StatefulWidget {
  final void Function()? onTap;
  RegisterPage({Key? key, required this.onTap}) : super(key: key);
  @override
  State<RegisterPage> createState() => _RegisterPageState();
}
class _RegisterPageState extends State<RegisterPage> {
  //text controllers
  RegisterController registerController = Get.put(RegisterController());
  //register method
  void register() {}
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      // Theme.of(context).colorScheme.background,
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(22),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Text(
                'Sign up',
                style: TextStyle(fontSize: 36, fontWeight: FontWeight.bold),
              ),
              const SizedBox(
                height: 25,
              ),
              MyEmailTextfield(
                  obscureText: false,
                  controller: registerController.emailController),
              const SizedBox(
                height: 10,
              ),
              MyConfirmEmailTextfield(
                  obscureText: false,
                  controller: registerController.configEmailController),
              const SizedBox(
                height: 10,
              ),
              MyPwTextfield(controller: registerController.passwordController),
              const SizedBox(
                height: 10,
              ),
              MyConfirmPwTextfield(
                  controller: registerController.configPasswordController),
              const SizedBox(
                height: 10,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  const Text(
                    "I am a Workshop Leader",
                    style: TextStyle(color: Colors.black),
                  ),
                  Switch(
                    value: registerController.isSeller,
                    onChanged: (bool newValue) {
                      setState(() {
                        registerController.isSeller = newValue;
                      });
                    },
                  ),
                ],
              ),
              const SizedBox(
                height: 20,
              ),
              MyButton(
                text: 'Register',
                onTap: register,
              ),
              const SizedBox(
                height: 20,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  const Text(
                    "Already have an account? ",
                    style: TextStyle(color: Colors.black),
                  ),
                  GestureDetector(
                    onTap: widget.onTap,
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
