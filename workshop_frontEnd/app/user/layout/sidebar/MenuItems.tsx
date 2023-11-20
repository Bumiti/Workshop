import {
  IconBoxMultiple, IconCircleDot, IconHome, IconInfoCircle, IconLayout, IconLayoutGrid, IconPhoto, IconPoint, IconStar, IconTable, IconUser
} from "@tabler/icons-react";

import { uniqueId } from "lodash";

const Menuitems = [
  {
    id: uniqueId(),
    title: "Dashboard",
    icon: IconHome,
    href: "/user",
  },
  {
    id: uniqueId(),
    title: "Workshop",
    icon: IconCircleDot,
    href: "/user/ui-components/buttons",
  },
  {
    id: uniqueId(),
    title: "Forms",
    icon: IconTable,
    href: "/user/ui-components/forms",
  },
  {
    id: uniqueId(),
    title: "Chat",
    icon: IconInfoCircle,
    href: "/user/ui-components/alerts",
  },
  {
    id: uniqueId(),
    title: "Rate",
    icon: IconStar,
    href: "/user/ui-components/ratings",
  },
  {
    id: uniqueId(),
    title: "Library",
    icon: IconPhoto,
    href: "/user/ui-components/images",
  },
  // {
  //   id: uniqueId(),
  //   title: "Pagination",
  //   icon: IconUser,
  //   href: "/ui-components/pagination",
  // },
  // {
  //   id: uniqueId(),
  //   title: "Tables",
  //   icon: IconLayoutGrid,
  //   href: "/ui-components/table",
  // },
];

export default Menuitems;
