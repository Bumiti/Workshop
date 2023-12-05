// ignore_for_file: must_be_immutable, avoid_print, deprecated_member_use

import 'package:flutter/material.dart';
import 'package:video_player/video_player.dart';
import 'package:workshop_mobi/app_localizations.dart';
import 'package:workshop_mobi/model/workshopResponses.dart';
import 'package:workshop_mobi/widgets/description_section.dart';
import 'package:workshop_mobi/widgets/videos_section.dart';

class CourseScreen extends StatefulWidget {
  CourseResponses courseResponses;
  CourseScreen(this.courseResponses);

  @override
  State<CourseScreen> createState() => _CourseScreenState();
}

class _CourseScreenState extends State<CourseScreen> {
  late VideoPlayerController _videoPlayerController;
  late Future<void> _initializeVideoPlayerFuture;

  @override
  void initState() {
    super.initState();

    _videoPlayerController = VideoPlayerController.network(
      widget.courseResponses.courseMediaInfos[0].urlMedia,
    );

    // Initialize the controller and store the Future for later use.
    _initializeVideoPlayerFuture = _videoPlayerController.initialize();

    // Use the controller to loop the video.
    _videoPlayerController.setLooping(true);
  }

  @override
  void dispose() {
    _videoPlayerController.dispose();
    super.dispose();
    _videoPlayerController.play();
  }

  bool isVideosSection = true;
  bool isPlaying = false;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        foregroundColor: Colors.black,
        backgroundColor: Colors.white,
        elevation: 0,
        centerTitle: true,
        title: Text(
          widget.courseResponses.name,
          style: const TextStyle(
            fontWeight: FontWeight.bold,
            letterSpacing: 1,
          ),
        ),
        actions: const [
          Padding(
            padding: EdgeInsets.only(right: 10),
            child: Icon(
              Icons.notifications,
              size: 28,
              color: Colors.black,
            ),
          )
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.symmetric(vertical: 10, horizontal: 20),
        child: ListView(
          children: [
            FutureBuilder(
              future: _initializeVideoPlayerFuture,
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.done) {
                  return // Thêm biến để theo dõi trạng thái play/pause

                      AspectRatio(
                    aspectRatio: _videoPlayerController.value.aspectRatio,
                    child: Stack(
                      alignment: Alignment.center,
                      children: [
                        VideoPlayer(_videoPlayerController),
                        if (!isPlaying)
                          IconButton(
                            icon: Icon(
                              Icons.play_circle_fill,
                              size: 50,
                              color: Colors.white,
                            ),
                            onPressed: () {
                              setState(() {
                                isPlaying =
                                    true; 
                                if (_videoPlayerController.value.isPlaying) 
                                {
                                  
                                  _videoPlayerController.pause();
                                  isPlaying = false;
                                } else {
                                  _videoPlayerController.play();
                                  isPlaying = true;
                                }
                              });
                            },
                          ),
                      ],
                    ),
                  );
                  ;
                } else {
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                }
              },
            ),
            const SizedBox(height: 15),
            WorkshopName(widget: widget),
            const SizedBox(height: 5),
            CreatebyTeacherName(widget: widget),
            const SizedBox(height: 5),
            CountVideo(widget: widget),
            const SizedBox(height: 20),
            Container(
              padding: const EdgeInsets.symmetric(vertical: 15, horizontal: 10),
              decoration: BoxDecoration(
                color: const Color(0xFFF5F3FF),
                borderRadius: BorderRadius.circular(10),
              ),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  Material(
                    // if Decriptions is true then that color will be on button and else another color
                    color: isVideosSection
                        ? const Color(0xFF674AEF)
                        : const Color(0xFF674AEF).withOpacity(0.6),
                    borderRadius: BorderRadius.circular(10),
                    child: InkWell(
                      onTap: () {
                        //change value of isVideosSection
                        setState(() {
                          isVideosSection = true;
                        });
                      },
                      child: Container(
                        padding: const EdgeInsets.symmetric(
                            vertical: 15, horizontal: 35),
                        child: const Text(
                          "Videos",
                          style: TextStyle(
                            color: Colors.white,
                            fontSize: 20,
                            fontWeight: FontWeight.w500,
                          ),
                        ),
                      ),
                    ),
                  ),
                  Material(
                    color: isVideosSection
                        ? const Color(0xFF674AEF).withOpacity(0.6)
                        : const Color(0xFF674AEF),
                    borderRadius: BorderRadius.circular(10),
                    child: InkWell(
                      onTap: () {
                        //change value of isVideosSection
                        setState(() {
                          isVideosSection = false;
                        });
                      },
                      child: Container(
                        padding: const EdgeInsets.symmetric(
                            vertical: 15, horizontal: 35),
                        child: const Text(
                          "Decriptions",
                          style: TextStyle(
                            color: Colors.white,
                            fontSize: 20,
                            fontWeight: FontWeight.w500,
                          ),
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            ),
            const SizedBox(height: 10),
            isVideosSection
                ? VideoSetion(
                    widget: widget,
                  )
                : DescriptionSection(
                    widget: widget,
                  ),
          ],
        ),
      ),
    );
  }
}

class CountVideo extends StatelessWidget {
  const CountVideo({
    super.key,
    required this.widget,
  });

  final CourseScreen widget;

  @override
  Widget build(BuildContext context) {
    return Text(
      "${widget.courseResponses.courseMediaInfos.length} Videos",
      style: TextStyle(
        fontSize: 15,
        fontWeight: FontWeight.w500,
        color: Colors.black.withOpacity(0.5),
      ),
    );
  }
}

class CreatebyTeacherName extends StatelessWidget {
  const CreatebyTeacherName({
    super.key,
    required this.widget,
  });

  final CourseScreen widget;

  @override
  Widget build(BuildContext context) {
    return Text(
      AppLocalizations.translateFullName(
        context,
        "Create by Dear ${widget.courseResponses.teacher}",
      ),
      style: const TextStyle(
        fontSize: 16,
        fontWeight: FontWeight.w500,
      ),
    );
  }
}

class WorkshopName extends StatelessWidget {
  const WorkshopName({
    super.key,
    required this.widget,
  });

  final CourseScreen widget;

  @override
  Widget build(BuildContext context) {
    return Text(
      "${widget.courseResponses.name} Complete Course",
      style: const TextStyle(
        fontSize: 22,
        fontWeight: FontWeight.w600,
      ),
    );
  }
}
