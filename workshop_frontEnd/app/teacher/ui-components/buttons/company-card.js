import PropTypes from 'prop-types';
import ArrowDownOnSquareIcon from '@heroicons/react/24/solid/ArrowDownOnSquareIcon';
import ClockIcon from '@heroicons/react/24/solid/ClockIcon';
import { Avatar, Box, Card, CardContent, Divider, Stack, SvgIcon, Typography } from '@mui/material';

export const CompanyCard = ({ courses, courseId }) => {
  const filteredCourses = courses.filter(course => course.id === courseId);
  console.log("Filtered courses: ", filteredCourses);
  const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleDateString('vi-VN'); // 'vi-VN' để định dạng ngày tháng theo ngôn ngữ Việt Nam
  };
  return (
    <Card
      sx={{
        display: 'flex',
        flexDirection: 'column',
        height: '100%'
      }}
    >
      <CardContent>
        {filteredCourses.map((company, index) => (
          <Box key={index}>
            <Box
              sx={{
                display: 'flex',
                justifyContent: 'center',
                pb: 3
              }}
            >
              <Avatar
                src={company.courseMediaInfos[0]?.urlImage}
                variant="square"
              />
            </Box>
            <Typography
              align="center"
              gutterBottom
              variant="h5"
            >
              {company.name}
            </Typography>
            <Typography
              align="center"
              variant="body1"
            >
              {company.description}
            </Typography>
          </Box>
        ))}
      </CardContent>
      <Box sx={{ flexGrow: 1 }} />
      <Divider />
     
       {filteredCourses.map((company, index) => (
          <Stack key={index}   alignItems="center"
          direction="row"
          justifyContent="space-between"
          spacing={2}
          sx={{ p: 2 }}>
            <Stack
              alignItems="center"
              direction="row"
              spacing={1}
            >
              <SvgIcon
                color="action"
                fontSize="small"
              >
                <ClockIcon />
              </SvgIcon>
              <Typography
                color="text.secondary"
                display="inline"
                variant="body2"
              >
                {formatDate(company.startDate)}
              </Typography>
            </Stack>
            <Stack
              alignItems="center"
              direction="row"
              spacing={1}
            >
              <SvgIcon
                color="action"
                fontSize="small"
              >
                <ArrowDownOnSquareIcon />
              </SvgIcon>
              <Typography
                color="text.secondary"
                display="inline"
                variant="body2"
              >
                {company.studentEnrollments.length} Enrolled
              </Typography>
            </Stack>
          </Stack>
        ))}
      
    </Card>
  );
};

CompanyCard.propTypes = {
  courses: PropTypes.array.isRequired,
  courseId: PropTypes.number.isRequired,
};
