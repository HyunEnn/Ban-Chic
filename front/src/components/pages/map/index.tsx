import styled from "styled-components";
import NaverMap from "../../atoms/navermap/NaverMap";

function MapPage() {
  return (
    <>
      <SDiv>
        <NaverMap />
      </SDiv>
    </>
  );
}

const SDiv = styled.div`
  width: 100%;
  height: calc(100vh - 50px);
`;

export default MapPage;
