// import 'package:flutter/material.dart';
// import 'package:lottie/lottie.dart';

// class IntroPage2 extends StatefulWidget {
//   @override
//   State<IntroPage2> createState() => _IntroPage2State();
// }

// class _IntroPage2State extends State<IntroPage2>
//     with SingleTickerProviderStateMixin {
//   //Controller for animation
//   late final AnimationController controller;

//   @override
//   void initState() {
//     super.initState();
//     controller = AnimationController(
//       duration: const Duration(seconds: 2),
//       vsync: this,
//     );
//   }

//   @override
//   void dispose() {
//     super.dispose();
//     controller.dispose();
//   }

//   bool bookmarked = false;

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       body: Center(
//           child: GestureDetector(
//         onTap: () {
//           if (bookmarked == false) {
//             bookmarked = true;
//             controller.forward();
//           } else {
//             bookmarked = false;
//             controller.reverse();
//           }
//         },
//         child: Lottie.network(
//             'https://lottie.host/87baefc1-8ee1-4690-9f4d-61fdc965670d/Zyoodcd3lm.json',
//             controller: controller),
//       )),
//     );
//   }
// }
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
