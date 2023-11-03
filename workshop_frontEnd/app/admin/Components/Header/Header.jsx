'use client'
import { useSession, signIn, signOut } from "next-auth/react";
import * as React from 'react';
import Box from '@mui/material/Box';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import Avatar from '@mui/material/Avatar';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';


import { BsJustify } from 'react-icons/bs'
import 'bootstrap/js/dist/dropdown'
import 'bootstrap/js/dist/collapse'

const settings = ['Profile', 'Account', 'Dashboard', 'Logout'];

function Header({ Toggle }) {
    const { data: session } = useSession();
    const [anchorElUser, setAnchorElUser] = React.useState(null);

    const handleOpenUserMenu = (event) => {
        setAnchorElUser(event.currentTarget);
    };

    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
    };

    return (
        <nav className="navbar navbar-expand-sm navbar-dark bg-dark p-2">
            <i className="navbar-brand header-icon rounded-circle text-center fs-4" onClick={Toggle}><BsJustify/></i>
            <div><a>Welcome</a></div>
            <div className="collapse navbar-collapse" id="collapsibleNavId">
                <ul className="navbar-nav ms-auto mt-2 mt-lg-0">
                <Box mt={'8px'} mr={'20px'} color={'white'}><Typography>Welcome: {session?.user?.name}</Typography></Box>
                    <Box sx={{ flexGrow: 0 }}>
                        <Tooltip title="Open settings">
                            <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                                <Avatar alt={session?.user?.name} src={session?.user?.picture || session?.user?.image} />
                            </IconButton>
                        </Tooltip>
                        <Menu
                            sx={{ mt: '45px' }}
                            id="menu-appbar"
                            anchorEl={anchorElUser}
                            anchorOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            open={Boolean(anchorElUser)}
                            onClose={handleCloseUserMenu}
                        >
                            {settings.map((setting) => (
                                <MenuItem key={setting} onClick={handleCloseUserMenu}>
                                    <Typography textAlign="center">{setting}</Typography>
                                </MenuItem>
                            ))}
                        </Menu>
                    </Box>
                </ul>
            </div>
        </nav>
    )
}
export default Header