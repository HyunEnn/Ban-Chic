import styled from "styled-components";

function GNB() {
  return <SHeaderContainer>헤더</SHeaderContainer>;
}

export const SHeaderContainer = styled.nav`
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: blue;
  height: 44px;
`;

export default GNB;
