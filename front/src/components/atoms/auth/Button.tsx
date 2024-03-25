import { ReactNode } from "react";
import styled from "styled-components";
import theme from "../../../styles/Theme";

interface IButtonProps {
  children: ReactNode;
  onClick: () => void;
}

function ButtonComponent({ children, onClick }: IButtonProps) {
  return <SButton onClick={onClick}>{children}</SButton>;
}

const SButton = styled.button`
  width: 100%;
  height: 100%;
  color: ${theme.color.bgColor};
  ${theme.styleBase.glassmorphism}
  border-radius: 5px;
`;

export default ButtonComponent;
