import { ReactNode } from "react";
import styled from "styled-components";

interface Props {
  children: ReactNode;
}

function MyReviewList({ children }: Props) {
  return <SList>{children}</SList>;
}

const SList = styled.article`
  width: 100%;
  height: 100%;
  background-color: blue;
`;

export default MyReviewList;
