import 'package:flutter/material.dart';
import 'package:lottie/lottie.dart';

class IntroPage3 extends StatelessWidget {
  const IntroPage3({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
          child: Lottie.network(
              'https://lottie.host/5b594c57-ab76-49fb-a3d1-f38f97608b34/g5e4sXGH2p.json')),
    );
  }
}
