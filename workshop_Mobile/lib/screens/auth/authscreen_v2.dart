import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:workshop_mobi/controller/login_controller.dart';
import 'package:workshop_mobi/screens/auth/signInwidgets/header.dart';
import 'package:workshop_mobi/screens/auth/signInwidgets/inputv2widgets.dart';

class SignIn extends StatefulWidget {
  const SignIn({super.key});

  @override
  State<SignIn> createState() => _SignInState();
}

class _SignInState extends State<SignIn> {
  LoginController loginController = Get.put(LoginController());

  @override
  Widget build(BuildContext context) {
    Size screenSize = MediaQuery.of(context).size;

    return Column(
      children: [
        Container(
          width: screenSize.width * 1, // 430 / screenSize.width
          height: screenSize.height * 1, // 932 / screenSize.height
          clipBehavior: Clip.antiAlias,
          decoration: const BoxDecoration(color: Colors.white),
          child: Stack(
            children: [
              PositionedHeadeWidget(screenSize: screenSize),
              InputPositionedWidget(screenSize: screenSize, textEditingController: loginController.emailController,),
              Positioned(
                left: screenSize.width * 0.15, // 65 / screenSize.width
                top: screenSize.height * 0.465, // 434 / screenSize.height
                child: Container(
                  width: screenSize.width * 0.698, // 300 / screenSize.width
                  padding: EdgeInsets.all(screenSize.width * 0.028), // 12 / screenSize.width
                  decoration: ShapeDecoration(
                    shape: RoundedRectangleBorder(
                      side: BorderSide(width: screenSize.width * 0.002, // 0.80 / screenSize.width
                          color: const Color(0xFFDEDEDE)),
                      borderRadius: BorderRadius.circular(screenSize.width * 0.012), // 5 / screenSize.width
                    ),
                  ),
                  child: Row(
                    mainAxisSize: MainAxisSize.min,
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Container(
                        padding: EdgeInsets.all(screenSize.width * 0.004), // 2 / screenSize.width
                        child: Row(
                          mainAxisSize: MainAxisSize.min,
                          mainAxisAlignment: MainAxisAlignment.center,
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            Text(
                              'Password',
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                color: Colors.black,
                                fontSize: screenSize.width * 0.028, // 12 / screenSize.width
                                fontFamily: 'SVN-Effra',
                                fontWeight: FontWeight.w300,
                                height: 0,
                              ),
                            ),
                          ],
                        ),
                      ),
                      Container(
                        width: screenSize.width * 0.046, // 20 / screenSize.width
                        height: screenSize.height * 0.012, // 12 / screenSize.height
                        clipBehavior: Clip.antiAlias,
                        decoration: const BoxDecoration(),
                        child: Stack(
                          children: [
                            Positioned(
                              left: screenSize.width * 0.002, // 1 / screenSize.width
                              top: screenSize.height * 0.001, // 1 / screenSize.height
                              child: SizedBox(
                                width: screenSize.width * 0.042, // 18 / screenSize.width
                                height: screenSize.height * 0.01, // 10 / screenSize.height
                                child: const Stack(children: [
                                  // Your Stack Children Here
                                ]),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ],
                  ),
                ),
              ),
              Positioned(
                left: screenSize.width * 0.628, // 271 / screenSize.width
                top: screenSize.height * 0.529, // 493 / screenSize.height
                child: Text(
                  'Forgot password?',
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    color: const Color(0xFF00AEEF),
                    fontSize: screenSize.width * 0.028, // 12 / screenSize.width
                    fontFamily: 'SVN-Effra',
                    fontWeight: FontWeight.w500,
                    height: 0,
                  ),
                ),
              ),
              Positioned(
                left: screenSize.width * 0.15, // 64 / screenSize.width
                top: screenSize.height * 0.653, // 608 / screenSize.height
                child: Text.rich(
                  TextSpan(
                    children: [
                      TextSpan(
                        text: 'Don’t have an account?',
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: screenSize.width * 0.028, // 12 / screenSize.width
                          fontFamily: 'SVN-Effra',
                          fontWeight: FontWeight.w500,
                          height: 0,
                        ),
                      ),
                      TextSpan(
                        text: ' Sign Up',
                        style: TextStyle(
                          color: const Color(0xFF00AEEF),
                          fontSize: screenSize.width * 0.028, // 12 / screenSize.width
                          fontFamily: 'SVN-Effra',
                          fontWeight: FontWeight.w500,
                          height: 0,
                        ),
                      ),
                    ],
                  ),
                  textAlign: TextAlign.center,
                ),
              ),
              Positioned(
                left: screenSize.width * 0.15, // 65 / screenSize.width
                top: screenSize.height * 0.574, // 524 / screenSize.height
                child: Container(
                  width: screenSize.width * 0.698, // 300 / screenSize.width
                  padding: EdgeInsets.symmetric(
                    horizontal: screenSize.width * 0.053, // 23 / screenSize.width
                    vertical: screenSize.height * 0.005, // 5 / screenSize.height
                  ),
                  clipBehavior: Clip.antiAlias,
                  decoration: ShapeDecoration(
                    gradient: const LinearGradient(
                      begin: Alignment(-0.00, -1.00),
                      end: Alignment(0, 1),
                      colors: [
                        Color(0xFF00AEEF),
                        Color(0x832EECF8),
                        Color(0x0028E898)
                      ],
                    ),
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(screenSize.width * 0.012), // 5 / screenSize.width
                    ),
                    shadows: [
                      BoxShadow(
                        color: const Color(0x3F000000),
                        blurRadius: screenSize.width * 0.016, // 7 / screenSize.width
                        offset: Offset(-screenSize.width * 0.014, // -6 / screenSize.width
                            screenSize.width * 0.014), // 6 / screenSize.width
                        spreadRadius: 0,
                      )
                    ],
                  ),
                  child: Row(
                    mainAxisSize: MainAxisSize.min,
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Text(
                        'Login',
                        textAlign: TextAlign.center,
                        style: TextStyle(
                          color: Colors.white,
                          fontSize: screenSize.width * 0.056, // 20 / screenSize.width
                          fontFamily: 'SVN-Effra',
                          fontWeight: FontWeight.w700,
                          height: 0,
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}




