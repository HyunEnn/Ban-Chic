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
`;

export default List;
