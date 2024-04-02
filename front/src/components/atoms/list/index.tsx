import { ReactNode } from "react";

import styled from "styled-components";

interface Props {
  children: ReactNode;
}

function List({ children }: Props) {
  return <SListContainer>{children}</SListContainer>;
}

const SListContainer = styled.div`
  padding: 1em;
  display: flex;
  gap: 10px;
  max-height: 375px;
  overflow-x: scroll;
  flex-wrap: wrap;
  &::-webkit-scrollbar {
    display: none;
  }
  -ms-overflow-style: none;
  scrollbar-width: none;
`;

export default List;
