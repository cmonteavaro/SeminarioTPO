import React from 'react';
import ProfileBody from '../components/Users/ProfileBody';
import { database } from '../Utils/SharedData';

const dummy_user = database.database_users

const Profile = () => {
        let refugio = dummy_user[0];
        return <ProfileBody refugio = {refugio} />
			;
		};

export default Profile;