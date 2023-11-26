'use client';
import { useCallback, useState } from 'react';
import {
  Box,
  Card,
  CardContent,
  CardHeader,
  Divider,
  FormControlLabel,
  Grid,
  Switch,
  TextField,
  Typography
} from '@mui/material';

export const AccountProfileDetails = ({ onDataChanged }) => {
  const [values, setValues] = useState({
    name: '',
    description: '',
    price: 0,
    startDate: '',
    endDate: '',
    student_count: 0,
    type: 'offline',
    courseLocation: [
      {
        courseLocation_id: 0,
        schedule_Date: '',
        area: ''
      }
    ]
  });

  const handleInputChange = useCallback((event) => {
    const newValue = event.target.type === 'checkbox' ? event.target.checked : event.target.value;
    const name = event.target.name;
  
    if (name === 'price') {
      const newPrice = parseInt(newValue);
      setValues((prevState) => ({
        ...prevState,
        price: newPrice,
      }));
      onDataChanged({ ...values, price: newPrice }); // Send updated price
    } else if (name === 'type') {
      const updatedType = values.type === 'online' ? 'offline' : 'online';
      setValues((prevState) => ({
        ...prevState,
        type: updatedType,
      }));
      onDataChanged({ ...values, type: updatedType }); // Send updated type
    } else {
      setValues((prevState) => ({
        ...prevState,
        [name]: newValue,
      }));
      onDataChanged({ ...values, [name]: newValue }); // Send updated values for other fields
    }
  }, [values, onDataChanged]);
  
  const handleLocationInputChange = useCallback((event, index) => {
    const { name, value } = event.target;
  
    setValues((prevState) => {
      const newCourseLocation = [...prevState.courseLocation];
      newCourseLocation[index] = {
        ...newCourseLocation[index],
        [name]: value,
      };
  
      const newState = {
        ...prevState,
        courseLocation: newCourseLocation,
      };
  
      onDataChanged(newState);
      return newState;
    });
  }, [onDataChanged]);
  

  return (
    <Card>
      <CardHeader subheader="The information can be edited" title="Profile" />
      <CardContent sx={{ pt: 0 }}>
        <Box sx={{ m: -1.5 }}>
          <Grid container spacing={3}>
            <Grid item xs={12} md={6}>
              <TextField
                fullWidth
                label="Course name"
                name="name"
                onChange={handleInputChange}
                required
                value={values.name}
              />
            </Grid>
            <Grid item xs={12} md={6}>
              <TextField
                fullWidth
                label="Description"
                name="description"
                onChange={handleInputChange}
                required
                value={values.description}
              />
            </Grid>
         
            <Grid item xs={12} md={6}>
              <TextField
                fullWidth
                label="Price"
                name="price"
                onChange={handleInputChange}
                required
                type="number"
                value={values.price}
              />
            </Grid>
            <Grid item xs={12} md={6}>
              <TextField
                fullWidth
                label="Start Date"
                name="startDate"
                onChange={handleInputChange}
                type="date"
                value={values.startDate}
                InputLabelProps={{ shrink: true }}
              />
            </Grid>
            <Grid item xs={12} md={6}>
              <TextField
                fullWidth
                label="End Date"
                name="endDate"
                onChange={handleInputChange}
                type="date"
                value={values.endDate}
                InputLabelProps={{ shrink: true }}
              />
            </Grid>
          </Grid>
        </Box>
      </CardContent>
      <Divider />
     
        <CardContent sx={{ pt: 0 }}>
          <CardHeader subheader="Please input your address for offline workshop" />
          <Box sx={{ m: -1.5 }}>
            {
              values.courseLocation.map((location, index) => (
                <Grid container spacing={3} key={index}>
                  <Grid item xs={12} md={6}>
                    <TextField
                      fullWidth
                      label="Schedule Date"
                      name="schedule_Date"
                      onChange={(event) => handleLocationInputChange(event, index)}
                      type="date"
                      value={location.schedule_Date}
                      InputLabelProps={{ shrink: true }}
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      fullWidth
                      label="Area"
                      name="area"
                      onChange={(event) => handleLocationInputChange(event, index)}
                      value={location.area}
                    />
                  </Grid>
                </Grid>
              ))
            }

          </Box>
        </CardContent>
      
    </Card>
  );
};