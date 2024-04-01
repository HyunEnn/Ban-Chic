import styled from "styled-components";
import GNB from "../components/molecules/common/gnb";
import Footer from "../components/molecules/common/footer";
import { Outlet } from "react-router";

function Layouts() {
  return (
    <SLayout>
      <GNB />
      <main>
        <Outlet />
      </main>
      <Footer />
    </SLayout>
  );
}

const SLayout = styled.div`
  width: 100%;

  &:nth-child(1) > :nth-child(2) {
    min-height: calc(100vh - (250px));
  }
`;

export default Layouts;
