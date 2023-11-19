import 'package:flutter/material.dart';

class PositionedHeadeWidget extends StatelessWidget {
  const PositionedHeadeWidget({
    super.key,
    required this.screenSize,
  });

  final Size screenSize;

  @override
  Widget build(BuildContext context) {
    return Positioned(
      left: screenSize.width * 0.288, // 124 / screenSize.width
      top: screenSize.height * 0.23,
       // 215 / screenSize.height
      child: SizedBox(
        width: screenSize.width * 0.424, // 182 / screenSize.width
        height: screenSize.height * 0.087, // 81 / screenSize.height
        child: Text(
          'Sign In ',
          textAlign: TextAlign.center,
          style: TextStyle(
            color: Colors.black,
            fontSize: screenSize.width * 0.084, // 36 / screenSize.width
            fontFamily: 'SVN-Effra',
            fontWeight: FontWeight.w700,
            height: 0,
            decoration: TextDecoration.none
          ),
        ),
      ),
    );
  }
}