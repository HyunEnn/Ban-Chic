import styled from "styled-components";
import KakaoMap from "../../atoms/kakaomap/KakaoMap";
import BrandLists from "../../atoms/kakaomap/BrandLists";

function MapPage() {
  return (
    <>
      <SDiv>
        <BrandLists />
        <KakaoMap />
      </SDiv>
    </>
  );
}

const SDiv = styled.div`
  width: 100%;
  height: calc(100vh - 50px);
`;

export default MapPage;
