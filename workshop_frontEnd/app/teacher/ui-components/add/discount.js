import { useState } from 'react';
import {
  Button,
  Card,
  CardContent,
  CardHeader,
  Divider,
  Grid,
  TextField,
} from '@mui/material';

export const DiscountDTOS = ({ onDataChanged }) => {
  const [discountData, setDiscountData] = useState({
    discountDTOS: [
      {
        name: '',
        description: '',
        quantity: 0,
        valueDiscount: 0,
        remainingUses: 0,
        redemptionDate: '',
      }]
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    const newData = {
      discountDTOS: [
        {
          ...discountData.discountDTOS[0], // Assuming there is only one object in the array
          [name]: value,
        },
      ],
    };
    setDiscountData(newData);
    onDataChanged(newData);
  };
  return (
    <Card>
      <CardHeader
        subheader="Manage Discounts"
        title="Discount Information"
      />
      <Divider />
      <CardContent>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <TextField
              fullWidth
              label="Discount Name"
              name="name"
              onChange={handleInputChange}
              value={discountData.name}
              variant="outlined"
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              fullWidth
              label="Description"
              name="description"
              onChange={handleInputChange}
              value={discountData.description}
              variant="outlined"
            />
          </Grid>
          <Grid item xs={6}>
            <TextField
              fullWidth
              label="Quantity"
              name="quantity"
              onChange={handleInputChange}
              value={discountData.quantity}
              type="number"
              variant="outlined"
            />
          </Grid>
          <Grid item xs={6}>
            <TextField
              fullWidth
              label="Value Discount"
              name="valueDiscount"
              onChange={handleInputChange}
              value={discountData.valueDiscount}
              type="number"
              variant="outlined"
            />
          </Grid>
          <Grid item xs={6}>
            <TextField
              fullWidth
              label="Remaining Uses"
              name="remainingUses"
              onChange={handleInputChange}
              value={discountData.remainingUses}
              type="number"
              variant="outlined"
            />
          </Grid>
          <Grid item xs={6}>
            <TextField
              fullWidth
              label="Redemption Date"
              name="redemptionDate"
              type="datetime-local"
              onChange={handleInputChange}
              value={discountData.redemptionDate}
              variant="outlined"
            />
          </Grid>
        </Grid>
      </CardContent>
      <Divider />

    </Card>
  );
};
