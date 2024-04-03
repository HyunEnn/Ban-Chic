import { MouseEventHandler } from "react";
import styled from "styled-components";

type perfumeProps = {
  select: string;
  click: MouseEventHandler;
  children: any;
};

function Perfume({ children, select, click }: perfumeProps) {
  return (
    <SLi onClick={click} $selected={select === children}>
      {children}
    </SLi>
  );
}

const SLi = styled.li<{ $selected: boolean }>`
  cursor: pointer;
  font-weight: ${(props) => props.$selected && "bold"};
`;

export default Perfume;
