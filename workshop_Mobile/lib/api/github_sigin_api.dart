import 'package:flutter/material.dart';
import 'package:github_signin_promax/github_signin_promax.dart';

class GitHubSignInApi {
  static Future<Map<String, dynamic>> login(BuildContext context) async {
    final params = GithubSignInParams(
      clientId: '13834b566cdff854a8a4',
      clientSecret: 'dc64f118e65380062195af2018233733ab5ccf8d',
      redirectUrl: 'http://localhost:3000/auth/github/callback',
      scopes: 'read:user,user:email',
    );
    try {
      
      final result = await Navigator.of(context).push(
        MaterialPageRoute(builder: (context) {
          return GithubSigninScreen(
            params: params,
          );
        }),
      );
      if (result is GithubSignInResponse) {
        return {
          'status': result.status,
          'accessToken': result.accessToken,
          'error': result.error,
          'email':result.email,
          'name':result.name,
          'picture':result.picture,
        };
      } else {
        return {
          'error': 'Login failed or canceled.',
        };
      }
    } catch (e) {
      return {
        'error': 'An error occurred during login: $e',
      };
    }
  }
}

