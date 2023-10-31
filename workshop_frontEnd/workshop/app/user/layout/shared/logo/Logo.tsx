import NextLink from "next/link";
import { styled } from "@mui/material/styles"
import NextImage from "next/image";

const LogoImage = styled(NextImage)(() => ({
  height: "37px",
  width: "80px",
}));

const LogoLink = styled(NextLink)(() => ({
  height: "40px",
  width: "180px",
  overflow: "hidden",
  display: "block",
}));

const Logo = () => {
  return (
    <LogoLink href="/">
      <LogoImage src="/logo.png" alt="logo" layout="responsive" width={80} height={37} />
    </LogoLink>
  );
};

export default Logo;
