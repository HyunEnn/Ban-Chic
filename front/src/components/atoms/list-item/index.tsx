import styled from "styled-components";

function ListItem() {
  return <SListItemContainer>item</SListItemContainer>;
}

const SListItemContainer = styled.div`
  display: flex;
  padding: 10px;
  border: 1px solid black;
`;

export default ListItem;
