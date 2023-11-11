import PropTypes from 'prop-types';
import ArrowDownOnSquareIcon from '@heroicons/react/24/solid/ArrowDownOnSquareIcon';
import ClockIcon from '@heroicons/react/24/solid/ClockIcon';
import { Avatar, Box, Card, CardContent, Divider, Stack, SvgIcon, Typography } from '@mui/material';
import { useRouter } from 'next/navigation';


export const CompanyCard = ({  courses, courseId }) => {
  const router = useRouter();

  const filteredCourses = courses.filter(course => course.id === courseId);
  console.log("Filtered courses: ", filteredCourses);
  const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleDateString('vi-VN');
  };
  return (
    
    <div onClick={() => router.push(`./edit/${courseId}`)} >
    <Card  sx={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
      <CardContent>
        {filteredCourses[0] && (
          <Box>
            <Box sx={{ display: 'flex', justifyContent: 'center', pb: 3 }}>
              <Avatar src={filteredCourses[0].courseMediaInfos[0]?.urlImage} variant="square" />
            </Box>
            <Typography align="center" gutterBottom variant="h5">
              {filteredCourses[0].name}
            </Typography>
            <Typography align="center" variant="body1">
              {filteredCourses[0].description}
            </Typography>
          </Box>
        )}
      </CardContent>
      <Box sx={{ flexGrow: 1 }} />
      <Divider />
      {filteredCourses[0] && (
        <Stack alignItems="center" direction="row" justifyContent="space-between" spacing={2} sx={{ p: 2 }}>
          <Stack alignItems="center" direction="row" spacing={1}>
            <SvgIcon color="action" fontSize="small">
              <ClockIcon />
            </SvgIcon>
            <Typography color="text.secondary" display="inline" variant="body2">
              {formatDate(filteredCourses[0].startDate)}
            </Typography>
          </Stack>
          <Stack alignItems="center" direction="row" spacing={1}>
            <SvgIcon color="action" fontSize="small">
              <ArrowDownOnSquareIcon />
            </SvgIcon>
            <Typography color="text.secondary" display="inline" variant="body2">
              {filteredCourses[0].studentEnrollments.length} Enrolled
            </Typography>
          </Stack>
        </Stack>
      )}
    </Card>
    </div>
  );
};
CompanyCard.propTypes = {
  courses: PropTypes.array.isRequired,
  courseId: PropTypes.number.isRequired,
};