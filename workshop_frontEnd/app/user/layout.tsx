'use client'
// import { Grid, Box } from '@mui/material';
// import SalesOverview from './dashboard/SalesOverview';
// import DailyActivity from './dashboard/DailyActivity';
// import BlogCard from './dashboard/Blog';
// import ProductPerformance from './dashboard/ProductPerformance';
// import PageContainer from './container/PageContainer';

// const Dashboard = () => {
//   return (
//     <PageContainer title="Dashboard" description="this is Dashboard">
//     <Box mt={3}>
//       <Grid container spacing={3}>
//         <Grid item xs={12} lg={12}>
//           <SalesOverview />
//         </Grid>
//         {/* ------------------------- row 1 ------------------------- */}
//         <Grid item xs={12} lg={4}>
//           <DailyActivity />
//         </Grid>
//         <Grid item xs={12} lg={8}>
//           <ProductPerformance />
//         </Grid>
//         <Grid item xs={12} lg={12}>
//           <BlogCard />
//         </Grid>
//       </Grid>
//     </Box>
//   </PageContainer>
//   )
// }

// export default Dashboard;

import { styled, Container, Box } from "@mui/material";
import React, { useState } from "react";
import Sidebar from "./layout/sidebar/Sidebar";
import Header from "./layout/header/Header";
import Footer from "./layout/footer/page";
// import Header from "@/app/(DashboardLayout)/layout/header/Header";
// import Sidebar from "@/app/(DashboardLayout)/layout/sidebar/Sidebar";
// import Footer from "./layout/footer/page";
import { ThemeProvider } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import { baselightTheme } from "../user/utils/theme/DefaultColors";

const MainWrapper = styled("div")(() => ({
  display: "flex",
  minHeight: "100vh",
  width: "100%",
}));

const PageWrapper = styled("div")(() => ({
  display: "flex",
  flexGrow: 1,
  paddingBottom: "60px",
  flexDirection: "column",
  zIndex: 1,
  backgroundColor: "transparent",
}));

interface Props {
  children: React.ReactNode;
}



export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const [isSidebarOpen, setSidebarOpen] = useState(true);
  const [isMobileSidebarOpen, setMobileSidebarOpen] = useState(false);
  return (
    <ThemeProvider theme={baselightTheme}>
          <CssBaseline />

    <MainWrapper className="mainwrapper">

      {/* ------------------------------------------- */}
      {/* Sidebar */}
      {/* ------------------------------------------- */}
      <Sidebar
        isSidebarOpen={isSidebarOpen}
        isMobileSidebarOpen={isMobileSidebarOpen}
        onSidebarClose={() => setMobileSidebarOpen(false)}
      />
      {/* ------------------------------------------- */}
      {/* Main Wrapper */}
      {/* ------------------------------------------- */}
      <PageWrapper className="page-wrapper">
        {/* ------------------------------------------- */}
        {/* Header */}
        {/* ------------------------------------------- */}
        <Header toggleMobileSidebar={() => setMobileSidebarOpen(true)} />
        {/* ------------------------------------------- */}
        {/* PageContent */}
        {/* ------------------------------------------- */}
        <Container
          sx={{
            paddingTop: "20px",
            maxWidth: "1200px",
          }}
        >
          {/* ------------------------------------------- */}
          {/* Page Route */}
          {/* ------------------------------------------- */}
          <Box sx={{ minHeight: "calc(100vh - 170px)" }}>{children}</Box>
          {/* ------------------------------------------- */}
          {/* End Page */}
          {/* ------------------------------------------- */}

          {/* ------------------------------------------- */}
          {/* Footer */}
          {/* ------------------------------------------- */}
          <Footer />
        </Container>
      </PageWrapper>
    </MainWrapper>
    </ThemeProvider>

  );
}
