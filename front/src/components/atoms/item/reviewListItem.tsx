import styled from "styled-components";

interface Props {
  item: {
    id: string;
  };
}

function ReviewListItem({ item }: Props) {
  return <SItemContainer>{item.id}</SItemContainer>;
}

const SItemContainer = styled.section`
  padding: 1em;
  background-color: red;
  border: white 1px solid;
  margin: 3px;
  border-radius: 5px;
`;

export default ReviewListItem;
