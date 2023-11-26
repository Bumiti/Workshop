
import 'package:flutter/material.dart';
import 'package:lottie/lottie.dart';

class IntroPage2 extends StatelessWidget {
  const IntroPage2({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
          child: Lottie.network(
              'https://lottie.host/376f4023-201e-4229-9500-06c509a0ce8d/EROasC3pf8.json')),
    );
  }
}
