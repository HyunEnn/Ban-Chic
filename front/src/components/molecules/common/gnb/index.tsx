/* eslint-disable react-refresh/only-export-components */
import Page_Url from "../../../../router/Url";
import SideBar from "./sideBar";
import { SHeaderContainer, SImgContainer, SLink } from "./GNB.styles"; //

function GNB() {
  return (
    <SHeaderContainer>
      <SLink to={Page_Url.Main}>Ban:Chic</SLink>
      <SImgContainer src="/logo_triangle_banchic.png" alt="Logo" />
      <SideBar />
    </SHeaderContainer>
  );
}

export default GNB;
